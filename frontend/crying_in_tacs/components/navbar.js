import { useAuth0 } from '@auth0/auth0-react';
import { Image } from '@chakra-ui/image';
import { Text } from '@chakra-ui/layout';
import React, { useState } from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem, UncontrolledDropdown } from 'reactstrap';
import LoginButton from './loginButton';
import LogoutButton from './logoutButton';
import Link from 'next/link';

const Example = (props) => {
  const { user, isAuthenticated, isLoading, getAccessTokenSilently } = useAuth0();

  const getToken = async () => {
    const accessToken = await getAccessTokenSilently({
      audience: "https://tacs.2021.com"
    })
    setToken(accessToken)
  }

  const [collapsed, setCollapsed] = useState(true);
  const [ token, setToken ] = useState("")

  const toggleNavbar = () => setCollapsed(!collapsed);

  return (
    <Navbar color="dark" light>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          width:"100%"
        }}
      >
          <NavbarBrand href="/" className="mr-auto"><Text color="white">SUPER AMIGUES</Text></NavbarBrand>
      <Nav>
        {
          isAuthenticated ?
            <>
              
              <Dropdown isOpen={!collapsed} toggle={toggleNavbar}>

                <>
                  <DropdownToggle color="grey">
                    <Image
                      h="35px"
                      w="35px"
                      src={user.picture}
                      cursor="pointer"
                      onClick={toggleNavbar}
                      style={{
                        borderRadius:"15px"
                      }}
                    />

                  </DropdownToggle>

                  <DropdownMenu positionFixed>
                    <DropdownItem header>{user.name}</DropdownItem>
                    <DropdownItem divider />
                    <DropdownItem>
                      <Link href="/decks" color="white" ><p style={{ paddingTop: "13px", cursor: "pointer" }}>Decks</p></Link>
                    </DropdownItem>
                    <DropdownItem divider />
                    <DropdownItem><LogoutButton />
                    </DropdownItem>
                  </DropdownMenu>
                </>
              </Dropdown>
            </>
            :
            <LoginButton/>
        }


      </Nav>
    </div>
    </Navbar>
  );
}

export default Example;