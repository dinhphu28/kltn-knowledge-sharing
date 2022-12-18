import axiosClient from "./axiosClient";

const userVoteStateApi = {
    get: (articleId, params) => {
        const url = `/articles/${articleId}/vote-state`;

        return axiosClient.get(url, {params});
    },

    put: (articleId, params, data) => {
        const url = `/articles/${articleId}/vote-state`;

        return axiosClient.put(url, data, {params});
    }
}

export default userVoteStateApi;