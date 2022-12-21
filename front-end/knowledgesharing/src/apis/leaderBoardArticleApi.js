import axiosClient from "./axiosClient";

const leaderBoardArticleApi = {
    getAll: () => {
        const url = "/leader-board-articles";

        return axiosClient.get(url);
    },

    put: (id, data) => {
        const url = `/leader-board-articles/${id}`;

        return axiosClient.put(url, data);
    },

    delete: (id) => {
        const url = `/leader-board-articles/${id}`;

        return axiosClient.delete(url);
    }
};

export default leaderBoardArticleApi;