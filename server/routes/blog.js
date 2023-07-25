const express = require('express');
const router = express.Router();

const blogCTL = require('../controller/blog.controller');
/* GET home page. */
router.get('/list',blogCTL.getListBlog);
module.exports = router;