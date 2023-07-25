const express = require('express');
const router = express.Router();

const comicCTL = require('../controller/comic.controller');
/* GET home page. */
router.get('/list',comicCTL.getListComic);
router.post('/add',comicCTL.addComics);
router.delete('/delete/:id',comicCTL.deleteComics);
router.patch('/update/:id',comicCTL.updateComics);
module.exports = router;