import Link from 'next/link';
import LogoutButton from './logoutButton';
import LoginButton from './loginButton';
import { useAuth0 } from "@auth0/auth0-react";
import { useState } from 'react';

const Navbar = () => {
  const { user, isAuthenticated, isLoading, getAccessTokenSilently } = useAuth0();
  const [ token, setToken ] = useState("")

  const getToken = async () => {
    const accessToken = await getAccessTokenSilently({
      audience: "https://tacs.2021.com"
    })
    setToken(accessToken)
  }

  return (
    <div style={{backgroundColor:'red'}}>
    {
      (
        isAuthenticated ? (
          <div>
            PANTALLA AUTENTICADO
            {
              isAuthenticated &&
              <div>
                <img src={user.picture} alt={user.name} />
                <h2>{user.name}</h2>
                <p>{user.email}</p>
                <button onClick={getToken}>GET TOKEN</button>
                <p>{JSON.stringify(token)}</p>
                <LogoutButton />
              </div>
              
            }
          </div>
        ) : <div> <LoginButton /> PANTALLA SIN AUTENTICAR </div>
      )
    }
     <nav className='flex items-center flex-wrap bg-green-300 p-3 '>
        <Link href='/'>
          <a className='inline-flex items-center p-2 mr-4 '>
            <span className='text-xl text-white font-bold uppercase tracking-wide'>
            </span>
          </a>
        </Link>
      </nav>
    </div>
  );
};

export default Navbar;
