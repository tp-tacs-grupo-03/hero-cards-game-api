import Link from 'next/link';
import LogoutButton from './logoutButton';
import LoginButton from './loginButton';
import { useAuth0 } from "@auth0/auth0-react";

const Navbar = () => {
    const { user, isAuthenticated, isLoading } = useAuth0();
  return (
    <>
    <p>
      isAuth:
      {JSON.stringify(isAuthenticated)}
    </p>
    <p>
      user:
      {JSON.stringify(user)}
    </p>

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
              TalwindCSS
            </span>
          </a>
        </Link>
      </nav>
    </>
  );
};

export default Navbar;
