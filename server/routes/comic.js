const express = require('express');
const router = express.Router();

const comicCTL = require('../controller/comic.controller');
/* GET home page. */
router.get('/list',comicCTL.getListComic);
router.patch('/update/:action',comicCTL.updateComics);
module.exports = router;