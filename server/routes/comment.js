const express = require("express");
const router = express.Router();

const CommentCTL = require("../controller/comment.controller");
router.get('/list',CommentCTL.getComment);
router.post('/add',CommentCTL.postComment);
router.get('/feedback',CommentCTL.getFeedback);
router.post('/post',CommentCTL.postFeedback);
module.exports = router