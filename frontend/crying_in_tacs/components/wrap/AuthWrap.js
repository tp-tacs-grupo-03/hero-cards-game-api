import { useAuth0 } from "@auth0/auth0-react"

export const AuthtWrap = ({Component, initialProps}) => {

    const { isAuthenticated, isLoading } = useAuth0()

    return !isAuthenticated && !isLoading
        ? 
        <div >
            <div style={{ display: "flex", justifyContent: "center", padding: "20px", fontSize: "30px" }}><p><b>Ruta privada:</b> Se necesita iniciar sesion para acceder</p></div>
        </div>
        : 
            <Component {...initialProps}/>
}