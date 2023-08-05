const express = require('express');
const router = express.Router();

const UserCTL = require("../controller/user.controller");

/* GET users listing. */
router.get('/list',UserCTL.getlistUser);
router.post('/login',UserCTL.login);
router.post('/register',UserCTL.register);
router.delete('/delete/:id',UserCTL.deleteUser);
router.patch('/update/:id',UserCTL.updateUser);
router.patch('/follow/:id',UserCTL.userFollowComics);
module.exports = router;
