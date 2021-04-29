import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import LoginButton from '../components/loginButton'
import LogoutButton from '../components/logoutButton'
import Navbar from '../components/navbar'


const Profile = () => {
  const { user, isAuthenticated, isLoading } = useAuth0();
  return (
    isAuthenticated ? (
      <div>
        PANTALLA AUTENTICADO
        <LogoutButton/>
        <img src={user.picture} alt={user.name} />
        <h2>{user.name}</h2>
        <p>{user.email}</p>
      </div>
    ) : (
      <div>
          PANTALLA SIN AUTENTICAR
        <LoginButton/>
      </div>
      )
  );
};

export default Profile;