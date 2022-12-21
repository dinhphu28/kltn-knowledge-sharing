import axiosClient from "./axiosClient";

const articleTagApi = {
    getAll: (params) => {
        const url = "/article-tags";

        return axiosClient.get(url, {params});
    },

    getById: (id) => {
        const url = `/article-tags/${id}`;

        return axiosClient.get(url);
    },

    post: (data) => {
        const url = "/article-tags";

        return axiosClient.post(url, data);
    }
};

export default articleTagApi;