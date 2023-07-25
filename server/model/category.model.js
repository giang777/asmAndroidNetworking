const connect = require('./connect');

const Category = connect.mongoose.Schema(
    {
        name:
        {
            type: String,
            required: true,
        },
    },
    {
        collection: "categorys"
    }
)

const CategoryModel = connect.mongoose.model("CategoryModel", Category);
module.exports = CategoryModel;