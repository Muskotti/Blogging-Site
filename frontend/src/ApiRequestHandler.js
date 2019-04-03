import ApiRequest from "./ApiRequest";

export default class ApiRequestHandler {

  //Returns an ApiRequest object for requesting all blog posts
  static getAllBlogPostsRequest() {
    return this.getRequestByRel('getPosts');
  }

  static getCreateBlogPostRequest() {
    return this.getRequestByRel('createPost');
  }

  static getRequestByRel(rel) {
    let apiRoot = '/api/';
    let request;

    fetch(apiRoot)
      .then(httpResp => httpResp.json())
      .then(resourceJson => {
        let link = this.getLinkFromArrayByRel(resourceJson.links, rel);
        request = new ApiRequest(link.href, link.type);
      });

    return request;
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
