const connect = require('./connect');
const ChapterModel = require('./chapter.model');
const CategoryModel = require('./category.model');

const Comic = new connect.mongoose.Schema(
    {
        name:
        {
            type: String,
            required: true,
        },
        describe:
        {
            type: String,
            required: true,
        },
        img:
        {
            type: String,
        },
        author:{
            type: String,
            required: true,
        },
        like:
        {
            type: Number,
            required: true,
            default: 0,
        },
        follow:{
            type: Number,
            required: true,
            default: 0,
        },
        status:{
            type: Boolean,
            required: true,
            default: true,
        },
        view:{
            type: Number,
            required: true,
            default: 0,
        },
        id_chapter: {
            type: connect.mongoose.Schema.Types.ObjectId,
            ref: ChapterModel
        },
        id_category: {
            type: connect.mongoose.Schema.Types.ObjectId,
            ref: CategoryModel
        },
        avatar_story:{
            type: String,
        }
    },
    {
        collection: "comics"
    }
);


const ComicModel = connect.mongoose.model("ComicModel", Comic);

module.exports = ComicModel;