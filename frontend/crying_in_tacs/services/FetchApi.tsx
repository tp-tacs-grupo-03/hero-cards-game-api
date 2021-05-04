import { updateTreeLayoutMeasurements } from "framer-motion/types/render/dom/projection/utils";

export type HttpMethod = 'POST' | 'PUT' | 'GET' | 'DELETE' | 'PATCH';

class FetchApi {
    async request(method: HttpMethod, url: RequestInfo): Promise<any> {
        const options: RequestInit = {
            method: method,
            headers: {
                'Content-Type': "application/json"
            },
            mode: "cors"
        };

        const data = await fetch(url, options)
        const response = await data.json()
        console.log(response);

        return response.results;
    }

    async RequestBodyParams(method: HttpMethod, url: RequestInfo, requestBody: Object): Promise<any> {
        const options: RequestInit = {
            method: method,
            body: JSON.stringify(requestBody),
            headers: {
                'Content-Type': "application/json",
                'Authorization': 'Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ink4TUprbHhzOVg4Y3dGZEp5OVZHNiJ9.eyJpc3MiOiJodHRwczovL2Rldi1qeDhmeXN2cS51cy5hdXRoMC5jb20vIiwic3ViIjoiZ29vZ2xlLW9hdXRoMnwxMTMwNTM0NTYyNTIyMzI2MjMwNjIiLCJhdWQiOlsiaHR0cHM6Ly90YWNzLjIwMjEuY29tIiwiaHR0cHM6Ly9kZXYtang4ZnlzdnEudXMuYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTYxOTk5Nzc4MywiZXhwIjoxNjIwMDg0MTgzLCJhenAiOiJIOEVYMDBEZWd0TmJzcDJQZFVDWnh2TnV1MVJJNnZkWiIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwifQ.yvne_7dgau3qismMQ8LKMUT-jZqn0VM9PZo-bFFAhRkOJx39Wr32svA5lRwc3zfQjA_zbddy6MSduPcpi7XtG1tYSRUBHyPHk-H9DGo538JxEqbc_pOvwH3hPEJMNJzNLhoTu68xh-EtrEoOrZ_xlpNjx5lWeX6rtvQC36eDuzzqlBjBzkxjF_25Ubl-5NWMjPQ1NFfhPGallV2uJ1yUbAEwLKv_ve-ev7Ni2uEaRUUapmYZFyEOMh4LE5jSSVGD_dyxxIKlgUVnGIqCS_cE577S5hx3ryJlP1StVHslHFh4TCJbQ66qU-Iqbwg5yt3qvXTX_PPsyN6e7YM_yinlzA'
            }          

        };

        const data = await fetch(url, options)
        const response = await data.json()
        console.log(response);

        return response.results;
    }
}

export default FetchApi;