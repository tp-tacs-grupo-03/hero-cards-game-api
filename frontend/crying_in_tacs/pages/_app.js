import '../styles/globals.css'
import { Auth0Provider } from "@auth0/auth0-react";
import Navbar from '../components/navbar';

function MyApp({ Component, pageProps }) {

  return (<Auth0Provider
    domain="dev-jx8fysvq.us.auth0.com"
    clientId="H8EX00DegtNbsp2PdUCZxvNuu1RI6vdZ"
    redirectUri="http://localhost:3000"
    audience="https://tacs.2021.com"
  >
    <Navbar/>
    <Component {...pageProps} />
  </Auth0Provider>
  )
}

export default MyApp;