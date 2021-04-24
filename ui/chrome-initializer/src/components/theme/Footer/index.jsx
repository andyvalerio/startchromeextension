import React, { useContext } from 'react';
import { Container } from 'components/common';
import { ThemeContext } from 'providers/ThemeProvider';
import { Wrapper, Flex, Links, Details } from './styles';
import social from './social.json';

export const Footer = () => {
  const { theme } = useContext(ThemeContext);

  return (
    <Wrapper id="contact">
      <Flex as={Container}>
        <Details theme={theme}>
          <span>
            Provided with{' '}
            <span aria-label="love" role="img">
              💖
            </span>{' '}
            by{' '}
            <a href="https://valerio.nu" rel="noopener noreferrer" target="_blank">
              Valerio Technologies AB
            </a>
          </span>
        </Details>
        <Links>
          {social.map(({ id, name, link, icon }) => (
            <a key={id} href={link} target="_blank" rel="noopener noreferrer" aria-label={`follow me on ${name}`}>
              <img width="24" src={icon} alt={name} />
            </a>
          ))}
        </Links>
      </Flex>
    </Wrapper>
  );
};
