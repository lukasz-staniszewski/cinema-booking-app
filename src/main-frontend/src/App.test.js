import {render, screen} from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText("if you do /cinema_client/?client_id=Unknown in backend you get:");
  expect(linkElement).toBeInTheDocument();
});
