import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  vus: 10, // количество виртуальных пользователей
  duration: '10s', // длительность теста
  iterations: 100
};

export default function () {
  http.get(`http://localhost:8080/1:1`); // отправляем GET-запрос
  sleep(1);
}