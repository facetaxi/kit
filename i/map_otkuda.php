<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Примеры. Геопоиск.</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <!--
        Подключаем API карт 2.x
        Параметры:
          - load=package.full - полная сборка;
	      - lang=ru-RU - язык русский.
    -->
    <script src="http://api-maps.yandex.ru/2.0/?load=package.full&lang=ru-RU"
            type="text/javascript"></script>

    <script type="text/javascript">
        // Как только будет загружен API и готов DOM, выполняем инициализацию
     x=45.034942;
	 y=38.976032;
	    ymaps.ready(init(x,y));

        function init (x,y) {
            var myMap = new ymaps.Map("map", {
                    center: [x, y], 
                    zoom: 20,
                    // Включим масштабирование карты колесом мыши
                    behaviors: ['default', 'scrollZoom']
                });

            myMap.events.add('click', function (e) {
                var coords = e.get('coordPosition');

                // Отправим запрос на геокодирование
                ymaps.geocode(coords).then(function (res) {
                    var names = [];
                    // Переберём все найденные результаты и
                    // запишем имена найденный объектов в массив names
                    res.geoObjects.each(function (obj) {
                        names.push(obj.properties.get('name'));
                    });
                    // Добавим на карту метку в точку, по координатам
                    // которой запрашивали обратное геокодирование
                    myMap.geoObjects.add(new ymaps.Placemark(coords, {
                        // В качестве контента иконки выведем
                        // первый найденный объект
                        iconContent: names[0],
                        // А в качестве контента балуна -
                        // имена всех остальных найденных объектов
                        balloonContent: names.reverse().join(', ')
                    }, {
                        preset: 'twirl#redStretchyIcon',
                        balloonMaxWidth: '250'
                    }));
                });
            });
        }
    </script>
</head>

<body>
 
 
<div id="map" style="width: 310px; height: 160px"></div>
</body>

</html>
