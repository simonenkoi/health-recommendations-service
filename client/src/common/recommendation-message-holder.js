const data = require("../resources/recommendations.json");
const _ = require("underscore-keypath");

export const getRecommendationMessage = key => {
    return _(data).valueForKeyPath(key);
}
