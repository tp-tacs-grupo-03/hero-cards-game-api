import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

const LogoutButton = () => {
  const { logout } = useAuth0();

  return (
    <a onClick={() => logout({ returnTo: "http://localhost:3000" })} >
        Log out
    </a>
  );
};

export default LogoutButton;