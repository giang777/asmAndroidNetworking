const express = require("express");
const router = express.Router();

const CommentCTL = require("../controller/comment.controller");
router.get('/list',CommentCTL.getComment);
router.post('/add',CommentCTL.postComment);
module.exports = router