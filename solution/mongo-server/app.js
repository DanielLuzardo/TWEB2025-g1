const express = require("express");
const cors = require("cors");
const logger = require("morgan");
const statsRouter = require("./routes/stats");
const recommendationsRouter = require("./routes/recommendations");
const profilesRouter = require("./routes/profiles");
const favoritesRouter = require("./routes/favorites");

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

app.get("/health", (req, res) => {
  res.json({ status: "OK", server: "mongo-server" });
});

module.exports = app;
