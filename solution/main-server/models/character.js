const mongoose = require('mongoose');

const CharacterSchema = new mongoose.Schema({
    characterId: { type: Number, required: true, unique: true },
    data: { type: Object, required: true },
    createdAt: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Character', CharacterSchema);
