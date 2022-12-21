import axiosClient from './axiosClient';

const nominatedArticleApi = {
    getAll: () => {
        const url = "/nominated-articles";

        return axiosClient.get(url);
    },

    put: (id, data) => {
        const url = `/nominated-articles/${id}`;

        return axiosClient.put(url, data);
    },

    delete: (id) => {
        const url = `/nominated-articles/${id}`;

        return axiosClient.delete(url);
    }
};

export default nominatedArticleApi;