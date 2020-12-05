import React from 'react';
import { Navbar as BsNavbar, NavbarBrand, NavLink } from 'reactstrap';

function Navbar() {
  return (
    <BsNavbar bg="dark" variant="dark" expand="md">
      <NavbarBrand href="/">PondMS</NavbarBrand>
      <NavLink href="/opportunity">Opportunities</NavLink>
      <NavLink href="/addSelfLearning">Self Learning</NavLink>
      <NavLink href="/skills">Skills</NavLink>
    </BsNavbar>
  )
}

export default Navbar;