import axiosClient from "./axiosClient";

const articleReportApi = {
    getAll: (params) => {
        const url = '/article-reports';

        return axiosClient.get(url, {params});
    },

    getById: (id) => {
        const url = `/article-reports/${id}`;

        return axiosClient(url);
    },

    post: (data) => {
        const url = '/article-reports';

        return axiosClient.post(url, data);
    },

    put: (id, data) => {
        const url = `/article-reports/${id}`;

        return axiosClient.put(url, data);
    },

    getAllOfArticle: (articleId) => {
        const url = `/article-reports/${articleId}`

        return axiosClient.get(url);
    }
};

export default articleReportApi;