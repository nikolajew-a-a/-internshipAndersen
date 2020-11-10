### Topic 1 1
Приложение содержит LinearLayout с двумя TextView (“Hello” и “Andersen”).<br />

<p align="center">
  <img src="/screens/topic_1_1_1.jpg" height="500">
</p>

### Topic 1 2
Приложение содержит Fragment, в котором расположен EditText. При вводе текста всплывает Toast в новым текстом в поле EditText.
Для отслеживания текста в поле используется TextWatcher.

<p align="center">
  <img src="/screens/topic_1_2_1.jpg" height="500">
</p>

### Topic 1 3
Приложение содержит RecyclerView, каждый элемент содежит рандомную фигуру (треугольник, круг или квадрат) и номер элемента. При нажатии на элемент отображается AlertDialogFragment с информацией об элементе.

<p align="center">
  <img src="/screens/topic_1_3_1.jpg" height="500" hspace="10">
  <img src="/screens/topic_1_3_2.jpg" height="500" hspace="10">
</p>

### Topic 1 4
Приложение демонстрирует работу с  Menu и DrawerLayout.

<p align="center">
  <img src="/screens/topic_1_4_1.jpg" height="500" hspace="10">
  <img src="/screens/topic_1_4_2.jpg" height="500" hspace="10">
  <img src="/screens/topic_1_4_3.jpg" height="500" hspace="10">
</p>

### Topic 1 5
Приложение демонстрирует работу с landscape ориентацией.

<p align="center">
  <img src="/screens/topic_1_5_1.jpg" height="500" hspace="10">
</p>
<p align="center">
  <img src="/screens/topic_1_5_2.jpg" height="250" hspace="10">
</p>

### Topic 3 1 и Topic 3 2
Приложение предназначено для проигрывания музыки. Activity содержит 4 кнопки - play, pause, stop, select track.
При нажатии кнопки select track происходит запуск второго приложения, в котором содержится список песен. Список песен отображается с помощью RecyclerView. Также имеется возможность с помощью Spinner, фильтровать список песен по группе и жанру.
При нажатии на трек, запускается первое приложение и начинается воспроизведение выбранного трека.

<p align="center">
  <img src="/screens/topic_3_1_1.jpg" height="500" hspace="10">
  <img src="/screens/topic_3_1_2.jpg" height="500" hspace="10">
</p>

### Topic 4 1
Данное приложение демонстрирует работу с сетью интернет. Приложение запрашивает список новостей с https://newsapi.org/ и отображает их с помощью RecyclerView. При нажатии новость отображается детальная информация о выбранной новости.
Для работы с сетью используется Retrofit, для загрузки изображений Picasso и Glide (были использованы обе библиотеки для сравнения результатов работы).
#### Примечание:
Ветка feature_topic_4_1_1 - простейшая реализация. <br />
Ветка feature_topic_8_1_1 - реализация с помощью архитектуры MVVM. Дополнительно реализовано кэширование в Room на 1 минуту. <br />
Ветка feature_topic_10_6_1 - реализация с помощью архитектуры MVVM и Dagger. Дополнительно реализованы тесты для слоев Domain и Data. <br />
Topic 6 1 - реализация с помощью архитектуры MVP. БЕЗ кэширования в Room. Дополнительно реализованы тесты для Presenter и Model.

<p align="center">
  <img src="/screens/topic_4_1_1.jpg" height="500" hspace="10">
  <img src="/screens/topic_4_1_2.jpg" height="500" hspace="10">
</p>

### Topic 5 1
Созданный кастомный View - круг с дополнительным атрибутом radius. Также переопределен атрибут background - атрибут задает цвет круга.

<p align="center">
  <img src="/screens/topic_5_1_1.jpg" height="500" hspace="10">
</p>

### Topic 5 4
Приложение демонстрирует работу с GoogleMaps. При нажатии кнопки "Местоположение" камера передвигается к текущему местоположению, а на карте отображается круг радиусом 10 км. Внутри круга в случайном порядке добавлены 5 маркеров.

<p align="center">
  <img src="/screens/topic_5_4_1.jpg" height="500" hspace="10">
</p>

### Topic 6 1 
См. описание "Topic 4 1"

