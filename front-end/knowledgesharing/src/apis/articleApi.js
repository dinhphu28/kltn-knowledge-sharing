import axiosClient from "./axiosClient";

const articleApi = {

    getById: (id) => {
        const url = `/articles/${id}`;

        return axiosClient.get(url);
    },

    getAll: (params) => {
        const url = '/articles';

        return axiosClient.get(url, {params});
    },

    post: (data) => {
        const url = '/articles';

        // return axiosClient.post(url, data, {
        //     headers: {
        //         'Authorization': 'Bearer ' + localStorage.getItem("token")
        //     }
        // })

        return axiosClient.post(url, data);
    },
    
    put: (articleId, data) => {
        const url = `/articles/${articleId}`;

        return axiosClient.put(url, data, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            }
        })
    },

    delete: (articleId) => {
        const url = `/articles/${articleId}`;

        return axiosClient.delete(url, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            }
        })
    },

    hideShow: (articleId, data) => {
        const url = `/articles/${articleId}/hide`;

        return axiosClient.put(url, data);
    }
};

export default articleApi;