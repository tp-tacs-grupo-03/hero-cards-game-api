import { useAuth0 } from "@auth0/auth0-react"

var prevAccessToken

export function useFetchTacsApi() {

    const audience = `https://tacs.2021.com`

    const { getAccessTokenSilently } = useAuth0()

    return async function fetchTacsApi(path, method, body = {}) {

        const accessToken = prevAccessToken ?? await getAccessTokenSilently({
            audience: audience
        })

        prevAccessToken = accessToken

        const httpbody = method != 'GET' ? { body: JSON.stringify(body) } : {}
        const httppath = `http://localhost:8080/api/${path}`

        console.log(prevAccessToken);
        
        try {
            const options = {
                method: method,
                headers: {
                    'Content-Type': "application/json",
                    'Authorization': `Bearer ${accessToken}`
                },
                mode: "cors",
                ...httpbody
            }

            const data = await fetch(httppath, options)
            return await data.json()
        } catch (e) {
            console.log(e.message)
        }
    }
}