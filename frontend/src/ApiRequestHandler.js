import ApiRequest from "./ApiRequest";

export default class ApiRequestHandler {

  //Returns an ApiRequest object for requesting all blog posts
  static async getAllBlogPostsRequest() {
    return this.getRequestByRel('getPosts');
  }

  static async getCreateBlogPostRequest() {
    return this.getRequestByRel('createPost');
  }

  static async getRequestByRel(rel) {
    let apiRoot = '/api/';

    const httpResp = await fetch(apiRoot);
    const resourceJson = await httpResp.json();
    const link = this.getLinkFromArrayByRel(resourceJson.links, rel);
    return new ApiRequest(link.href, link.type);
  }

  static getLinkFromArrayByRel(links, rel) {
    let link = null;

    for (let _link of links) {
      if (_link.rel === rel) {
        link = _link;
      }
    }

    return link;
  }
}
