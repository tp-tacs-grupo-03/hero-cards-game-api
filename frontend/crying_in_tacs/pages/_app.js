import '../styles/globals.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Auth0Provider } from "@auth0/auth0-react";
import Navbar from '../components/navbar';
import { Box, ChakraProvider, Container } from "@chakra-ui/react"
import { DefaultWrap } from '../components/wrap/DefaultWrap';

function MyApp({ Component, pageProps }) {

  const Wrap = Component.Wrap || DefaultWrap

  return (
  <ChakraProvider>
      <Auth0Provider
        domain="dev-jx8fysvq.us.auth0.com"
        clientId="H8EX00DegtNbsp2PdUCZxvNuu1RI6vdZ"
        redirectUri="http://localhost:3000"
        audience="https://tacs.2021.com"
      >
        <Box bgColor="black" h="100vh" w="100vw">
        <Navbar />
        <Container marginTop="15px" minH="90%" minW="98%" bgColor="white" borderRadius="3px" centerContent>
            <Wrap {...pageProps} Component={Component} />
        </Container>
        </Box>
      </Auth0Provider>
  </ChakraProvider>
  )
}

export default MyApp;