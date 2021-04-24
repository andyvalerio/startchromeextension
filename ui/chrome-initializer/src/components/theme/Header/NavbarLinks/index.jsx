import React, {useContext} from 'react';
import AnchorLink from 'react-anchor-link-smooth-scroll';
import { ThemeContext } from 'providers/ThemeProvider';
import ToggleTheme from 'components/theme/Header/ToggleTheme';
import { Wrapper } from './styles';

const NavbarLinks = ({ desktop }) => {
  const { theme } = useContext(ThemeContext);

  return (
    <Wrapper desktop={desktop} theme={theme}>
      <AnchorLink href="#choose">Generate</AnchorLink>
      <AnchorLink href="#generate">Install</AnchorLink>
      <AnchorLink href="#contact">Get in touch</AnchorLink>
      <ToggleTheme />
    </Wrapper>
  )
};

export default NavbarLinks;
