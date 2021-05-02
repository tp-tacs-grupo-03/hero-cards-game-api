import { useAuth0 } from '@auth0/auth0-react';
import { Image } from '@chakra-ui/image';
import { Text } from '@chakra-ui/layout';
import React, { useState } from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import LoginButton from './loginButton';
import LogoutButton from './logoutButton';

const Example = (props) => {
  const { user, isAuthenticated } = useAuth0()
  const [collapsed, setCollapsed] = useState(true);

  const toggleNavbar = () => setCollapsed(!collapsed);

  return (
      <Navbar color="dark" light>
      <NavbarBrand href="/" className="mr-auto"><Text color="white">TACS</Text></NavbarBrand>
      <Nav><p>Black Lives Matter.Support the Equal Justice Initiative.</p></Nav>

      <Dropdown isOpen={!collapsed} toggle={toggleNavbar}>
          {
            isAuthenticated ?
            <DropdownToggle color="grey" borderRadius="25px">
            <Image
                h="35px"
                w="35px"
                src={user.picture}
                cursor="pointer"
                onClick={toggleNavbar}
                borderRadius="15px"
              /> 
              </DropdownToggle>
              :
              <LoginButton />
          }
        <DropdownMenu positionFixed>
          <DropdownItem header>{user.name}</DropdownItem>
          <DropdownItem divider />
          <DropdownItem><LogoutButton /></DropdownItem>
        </DropdownMenu>
      </Dropdown>
      </Navbar>
  );
}

export default Example;