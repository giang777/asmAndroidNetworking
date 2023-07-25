const express = require('express');
const router = express.Router();

const chapterCTL = require('../controller/chapter.controller');
/* GET home page. */
router.get('/',chapterCTL.getListChapter);

module.exports = router