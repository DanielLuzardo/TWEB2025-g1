const express = require("express");
const cors = require("cors");
const logger = require("morgan");
const statsRouter = require("./routes/stats");
const recommendationsRouter = require("./routes/recommendations");
const profilesRouter = require("./routes/profiles");
const favoritesRouter = require("./routes/favorites");
const ratingsRouter = require("./routes/ratings");
var path = require('path');

const app = express();
//Middlewares execute before the requests
app.use(logger("dev"));
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
//Routes
app.use("/stats", statsRouter);
app.use("/recommendations", recommendationsRouter);
app.use("/profiles", profilesRouter);
app.use("/favorites", favoritesRouter);
app.use("/ratings", ratingsRouter);

app.get("/health", (req, res) => {
  res.json({ status: "OK", server: "mongo-server" });
});

module.exports = app;

// server.js (excerpt)
const swaggerJSDoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');
const swaggerOptions = {
    definition: {
        openapi: '3.0.0',
        info: { title: 'Mongo Server', version: '1.0.0' },
        servers: [{ url: 'http://localhost:3000' }],
    },
    apis: [path.join(__dirname, 'routes/*.js')],
};
const swaggerSpec = swaggerJSDoc(swaggerOptions);
console.log("--- DEBUG SWAGGER ---");
console.log("Rutas encontradas:", swaggerSpec.paths);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));