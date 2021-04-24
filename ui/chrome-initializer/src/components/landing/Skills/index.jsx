import React, { useContext } from 'react';
import { ThemeContext } from 'providers/ThemeProvider';
import { Container } from 'components/common';
import dev from 'assets/illustrations/howto.gif';
import { Wrapper, SkillsWrapper, Details, Thumbnail } from './styles';

export const Skills = () => {
  const { theme } = useContext(ThemeContext);

  return (
    <Wrapper id="generate">
      <SkillsWrapper as={Container}>
        <Thumbnail>
          <img src={dev} alt="How to install your new Chrome extension" />
        </Thumbnail>
        <Details theme={theme}>
          <h1>Install the new extension</h1>
          <p>
            <ul>
              <li>Open chrome://extensions</li>
              <li>Enable Developer mode, top-right corner</li>
              <li>Drag-and-drop the extension.zip file there</li>
            </ul>
          </p>
        </Details>
      </SkillsWrapper>
    </Wrapper>
  );
};
