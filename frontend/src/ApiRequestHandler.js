import ApiRequest from "./ApiRequest";

export default class ApiRequestHandler {

  //Returns an ApiRequest object for requesting all blog posts
    static async getAllBlogPostsRequest() {
        return this.getRequestByRel('getPosts');
    }

    static async createBlogPostRequest() {
        return this.getRequestByRel('createPost');
    }

    static async getRequestByRel(rel) {
        let apiRoot = './api/';

        const httpResp = await fetch(apiRoot, {mode: "no-cors"});
        const resourceJson = await httpResp.json();
        const link = resourceJson.links.find((linkObject) => linkObject.rel === rel);

        return new ApiRequest(link.href, link.type);
    }
}
