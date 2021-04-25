import React, { useContext } from 'react';
import { ThemeContext } from 'providers/ThemeProvider';
import { Header } from 'components/theme';
import { Container } from 'components/common';
import dev from 'assets/illustrations/dev.svg';
import { Wrapper, IntroWrapper, Details, Thumbnail } from './styles';
import ContactForm from './ContactForm';

export const Choose = () => {
  const { theme } = useContext(ThemeContext);

  return (
    <Wrapper>
      <Header />
      <IntroWrapper as={Container} id="choose">
        <Details theme={theme}>
          <h1>Generate a new Chrome extension</h1>
          <p>Choose a few settings for your project</p>
          <ContactForm />
        </Details>
        <Thumbnail>
          <img src={dev} alt="Choose a few settings for your project" />
        </Thumbnail>
      </IntroWrapper>
    </Wrapper>
  );
};
