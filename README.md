# Особенности приложении

1)Приложение использует базу данных H2

2)Приложение использует flyway миграции,чтобы определить схему базы данных и заполнить её некоторыми тестовыми данными.

3)Приложение использует Spring Security на основе `Spring Security Login Form`.(**_доп задание_**)

4)Разграничение доступа реализовано на основе `GrantedAuthority`

5)Сущности получаются из базы в виде Графов(EntityGraph)

6)Настроен Swagger,url = `http://localhost:8080/swagger-ui/index.html` .(**_доп задание_**)

7)Настроена валидация DTO

7)В базе лежат два пользователя: 
ADMIN с никнеймом admin и паролем admin,а также полным доступом к эндпоинтам
 и USER с никнеймом user и паролем user с ограниченным доступом
# Выполненные задания

## _API ДЛЯ ПОЛЬЗОВАТЕЛЯ_

1) ### Регистрация нового пользователя(**POST**).

URL = `http://localhost:8080/user`

Запрос: 

`   {    
  "userName": "TestUser",   
  "email": "mytest@mail.ru",    
  "password": "user"        
}`

Ответ:

`"4b63059d-7b09-4e8f-8c44-7810889897da"`

В случае если userName или email не уникальны:

### 2)Просмотр баланса своего кошелька(**GET**)

URL = `http://localhost:8080/cryptoWallet/balance/{walletOwnerId}`

Запрос:

`http://localhost:8080/cryptoWallet/balance/3fd610fa-315c-4813-9a64-6eb453b44f88`

Ответ:

`{
  "balance": 1000000,
  "cryptoCounts": [
    {
      "currencyAbbreviation": "BTC",
      "cryptoBalance": 0.0001
    },
    {
      "currencyAbbreviation": "ETH",
      "cryptoBalance": 0.001
    }
  ]
}`

### 3)Пополнение своего кошелька(POST)

URL = `http://localhost:8080/cryptoWallet/balance`

Запрос:

`{
  "secretKey": "e4849d3b-625f-4a37-b860-e70415ae8dce",
  "amountToReplenishTheBalance": 2222
}`

Ответ(С учетом того что у пользователя на счету уже было 10000 валюты и 0.0001 BTC):

`{
  "balance": 12222,
  "cryptoCounts": [
    {
      "currencyAbbreviation": "BTC",
      "cryptoBalance": 0.0001
    }
  ]
}`

 ### 4)Вывод денег на карту(POST)
 
 URL = `http://localhost:8080/cryptoWallet/transfer`

Запрос:

`{
  "walletOwnerSecretKey": "e4849d3b-625f-4a37-b860-e70415ae8dce",
  "money": 1500,
  "creditCard": "1234 5678 1323 4456"
}`

Ответ(Учитывая что на счету у пользователя было 10000 валюты):

` {
         "balance": 8500
       }`
       
       
### 5)Просмотр курса валют

Запрос на URL = 

`http://localhost:8080/cryptoCurrency/`

Ответ:

`[
  {
    "abbreviation": "BTC",
    "value": 1800000
  },
  {
    "abbreviation": "ETH",
    "value": 122000
  },
  {
    "abbreviation": "USDT",
    "value": 75000
  },
  {
    "abbreviation": "XMR",
    "value": 11300
  }
] `      

### 6)Обмен валют по установленному курсу(POST)

URL для продажи = 
`http://localhost:8080/cryptoWallet/currency/sale`

URL для покупки =

`http://localhost:8080/cryptoWallet/currency/purchase`

Стоимость закрепляется за каждой валютой.Реализованы два эндпоинта на покупку и продажу криптовалюты 

а)Покупка криптовалюты(XMR) на 5000

Запрос:

`{
  "secretKey": "e4849d3b-625f-4a37-b860-e70415ae8dce",
  "currencyAbbreviation": "XMR",
  "amountFrom": 5000
}`

Ответ:

`{
  "currencyAbbreviation": "XMR",
  "amountFrom": 5000,
  "amountTo": 0.4424778761,
  "balanceAfterOperation": 5000,
  "cryptoBalanceAfterOperation": 0.4424778761
}`

б)Продажа криптовалюты

Запрос:

`{
  "secretKey": "e4849d3b-625f-4a37-b860-e70415ae8dce",
  "currencyAbbreviation": "XMR",
  "amountFrom": 0.35
}`

Ответ:

`{
  "currencyAbbreviation": "XMR",
  "amountFrom": 0.35,
  "amountTo": 0.0924778761,
  "balanceAfterOperation": 8955,
  "cryptoBalanceAfterOperation": 0.0924778761
}`

## API ДЛЯ АДМИНИСТРАТОРА.

### 1)Изменить курс валют(**POST**)

URL = `http://localhost:8080/cryptoCurrency/`

Запрос:

`{
  "abbreviation": "XMR",
  "newCurrencyValue": 12000
}`

Ответ:

`{
  "abbreviation": "XMR",
  "value": 12000
}`

### 2)Посмотреть общую сумму на всех пользовательских счетах для указанной валюты(**POST**)

В задании написано что это должен быть GET метод с названием валюты в теле.
 Так как у GET метода нет тела сделал GET названием криптовалюты в url.
 
 Запрос:
 
 `http://localhost:8080/cryptoWallet/balance/total/BTC`
 
 Ответ:
 
 `0.0936778761`

### 3)Посмотреть количество операций, которые были проведены за указанный период(например, за последние сутки)(**POST**).

URL = `http://localhost:8080/log/`

Я создал таблицу `log_schema.operation_detail` 
в которую сохраняю ID того,кто выполнил эту операцию,ее краткое описание,баланс после выполнения операции и время когда была выполнена операция. 
Решил что так нагляднее чем просто выводить количество операций.
В базе уже лежат две записи о покупке администратором криптовалюты,датированные 28 февраля 2021 года и 1 марта 2023 года соответственно.

За операцию считается: 
    а)Перевод денег со счета на карту
    б)Покупка криптовалюты
    в)Продажа криптовалюты
    г)Пополнение баланса
    
Запрос:

`{
  "dateFrom": "2020-01-01",
  "dateTo": "2023-01-01"
} `  

Ответ:

`[
  {
    "id": 1,
    "operationPerformerId": "3fd610fa-315c-4813-9a64-6eb453b44f88",
    "operationType": "Buy Crypto Currency",
    "callbackTime": "2021-02-28",
    "balanceAfterOperation": 1200000
  },
  {
    "id": 2,
    "operationPerformerId": "3fd610fa-315c-4813-9a64-6eb453b44f88",
    "operationType": "Buy Crypto Currency",
    "callbackTime": "2021-03-01",
    "balanceAfterOperation": 1000000
  }
]` 

На всякий случай сделал вариант и с подсчетом количества транзакций:

URL = `http://localhost:8080/log/count`

Запрос:

`{
  "dateFrom": "2020-01-01",
  "dateTo": "2023-01-01"
}`

Ответ:

`2`
