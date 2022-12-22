import axiosClient from "./axiosClient";

const newlyArticleApi = {
    getAll: () => {
        const url = "/new-articles";

        return axiosClient.get(url);
    }
};

export default newlyArticleApi;