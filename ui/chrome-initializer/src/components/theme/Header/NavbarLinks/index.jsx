import React, {useContext} from 'react';
import AnchorLink from 'react-anchor-link-smooth-scroll';
import { ThemeContext } from 'providers/ThemeProvider';
import ToggleTheme from 'components/theme/Header/ToggleTheme';
import { Wrapper } from './styles';

const NavbarLinks = ({ desktop }) => {
  const { theme } = useContext(ThemeContext);

  return (
    <Wrapper desktop={desktop} theme={theme}>
      <AnchorLink href="#choose">Choose</AnchorLink>
      <AnchorLink href="#generate">Generate</AnchorLink>
      <AnchorLink href="#contact">Get in touch</AnchorLink>
      <ToggleTheme />
    </Wrapper>
  )

};

export default NavbarLinks;
