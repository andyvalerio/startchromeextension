import React from 'react';
import axios from 'axios';
import { Formik, Form, FastField, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { Button, Input } from 'components/common';
import { saveAs } from 'file-saver';
import { Error, Center, InputField } from './styles';

export default () => (
  <Formik
    initialValues={{
      name: '',
      description: '',
      success: false,
    }}
    validationSchema={Yup.object().shape({
      name: Yup.string().required('Give a name to your extension'),
      description: Yup.string().required('Description field is required'),
    })}
    onSubmit={async ({ name, description }, { setSubmitting, resetForm, setFieldValue }) => {
      try {
        const formData = new FormData();
        formData.append('name', name);
        formData.append('description', description);
        const response = await axios({
          method: 'POST',
          url: `${process.env.GATSBY_BACKEND_ENDPOINT}`,
          responseType: 'blob',
          data: formData,
        });
        saveAs(response.data, 'extension.zip');
        setSubmitting(false);
        setFieldValue('success', true);
        document.location.hash = '#generate';
        // setTimeout(() => resetForm(), 6000);
      } catch (err) {
        setSubmitting(false);
        setFieldValue('success', false);
				alert(err) // eslint-disable-line
      }
    }}
  >
    {({ touched, errors, isSubmitting }) => (
      <Form>
        <InputField>
          <Input
            as={FastField}
            type="text"
            name="name"
            component="input"
            aria-label="name"
            placeholder="Extension name"
            error={touched.name && errors.name}
          />
          <ErrorMessage component={Error} name="name" />
        </InputField>
        <InputField>
          <Input
            id="description"
            aria-label="description"
            component="input"
            as={FastField}
            type="text"
            name="description"
            placeholder="Description"
            error={touched.description && errors.description}
          />
          <ErrorMessage component={Error} name="description" />
        </InputField>
        <Center>
          <Button secondary type="submit" disabled={isSubmitting}>
            Generate my new Chrome extension
          </Button>
        </Center>
      </Form>
    )}
  </Formik>
);
