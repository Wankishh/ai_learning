# 🧠 AI Engineer Roadmap – Практически Път с Проекти

> 🎯 Фокус: За Senior Developer с опит (JavaScript, Python, Java), който иска да влезе в света на AI чрез практически проекти и постепенно да навлиза в теорията, когато е необходимо.

---

## ✅ Легенда:
- [ ] Не е започнато
- [~] В процес
- [x] Завършено

---

## 🔹 Фаза 0 – Подготовка и Инфраструктура

### [x] Setup среда и инструменти
- Google Colab / Jupyter Notebook
- Python + pip + virtualenv
- VS Code / Cursor / GitHub Copilot
- Hugging Face акаунт

### 🔨 Мини-проект: Data Explorer
**Цел:** Зареждане и визуализация на dataset  
**Какво ще научиш:** Pandas, визуализация, basic data handling  
**Инструменти:** `pandas`, `matplotlib`, `seaborn`, `sklearn.datasets`  
**Описание:** Зареди Iris dataset. Направи скрипт, който:
- Показва базова статистика
- Рисува графики (разпределения, корелации)
- Експортира PDF отчет (опционално)

---

## 🔹 Фаза 1 – Класическо Машинно Обучение (ML)

### [ ] Проект 1: AI за предсказване на цени на имоти
**Цел:** Regression модел за housing prices  
**Какво ще научиш:** feature engineering, моделиране, evaluate-ване  
**Инструменти:** `scikit-learn`, `pandas`, `matplotlib`, `RandomForestRegressor`, `LinearRegression`  
**Dataset:** Kaggle Housing Prices  
**Описание:**
- Зареждане и почистване на данните
- Обработка на липсващи стойности и категориални променливи
- Изграждане на ML модел
- Изчисляване на MAE/MSE
- Визуализация на най-важните features

---

## 🔹 Фаза 2 – Deep Learning (DL)

### [ ] Проект 2: Класификация на ръкописни цифри (MNIST)
**Цел:** Създаване на CNN модел  
**Какво ще научиш:** PyTorch/TensorFlow, backpropagation, activations  
**Инструменти:** `PyTorch` или `TensorFlow`, `torchvision`, `matplotlib`  
**Описание:**
- Зареди `MNIST` dataset
- Направи simple CNN архитектура
- Обучи и валидира модела
- Покажи грешно класифицирани изображения
- Визуализирай training loss & accuracy

---

## 🔹 Фаза 3 – NLP и Transformers

### [ ] Проект 3: Разпознаване на токсични коментари
**Цел:** Binary text classification  
**Какво ще научиш:** NLP pipeline, tokenizer-и, zero-shot/fine-tuning  
**Инструменти:** `transformers`, `datasets`, `BERT`, `TextClassificationPipeline`  
**Dataset:** Jigsaw Toxic Comment Classification (Kaggle)  
**Описание:**
- Зареждане на предварително обучен модел (например `bert-base-uncased`)
- Класификация на коментари като токсични / нетоксични
- Изграждане на mini UI с gradio или streamlit (опционално)

---

## 🔹 Фаза 4 – Големи езикови модели (LLMs)

### [ ] Проект 4: GPT AI Асистент за PDF документи
**Цел:** Създаване на помощник, който отговаря на въпроси по PDF  
**Какво ще научиш:** prompt engineering, document indexing, LangChain  
**Инструменти:** `OpenAI API`, `LangChain`, `pdfplumber` или `PyMuPDF`, `Streamlit`  
**Описание:**
- Зареждане на PDF и извличане на съдържание
- Векторизация с FAISS
- Генериране на отговори чрез GPT-4 на база на документа

---

## 🔹 Фаза 5 – Компютърно зрение (CV)

### [ ] Проект 5: Разпознаване на емоции от лица
**Цел:** Image classification с CNN  
**Какво ще научиш:** image preprocessing, augmentations, CNN  
**Инструменти:** `OpenCV`, `Keras`/`PyTorch`, `FER2013`  
**Описание:**
- Обработка на изображения
- Изграждане на CNN за емоции: happy, sad, angry и т.н.
- Визуализация на predictions в реално време (webcam - опционално)

---

## 🔹 Фаза 6 – Разгръщане и интеграция

### [ ] Проект 6: AI backend с FastAPI
**Цел:** Разгръщане на AI модел като REST API  
**Какво ще научиш:** inference, dockerization, API security  
**Инструменти:** `FastAPI`, `Docker`, `Uvicorn`, AI модел от предишен проект  
**Описание:**
- REST API, който получава input и връща inference
- Документация чрез Swagger UI
- Docker файл за deploy

---

## 🔹 Бонус: AI агенти

### [ ] Проект 7: Автономен AI агент
**Цел:** Многостъпкова логика и планиране  
**Какво ще научиш:** tool calling, LangChain agents, planning  
**Инструменти:** `LangChain`, `OpenAI functions`, `Auto-GPT`  
**Описание:**
- Агент, който анализира уеб страници, прави изводи и съставя отчет
- Стъпково reasoning + извикване на инструменти