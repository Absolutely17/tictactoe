API:

GET /games - получить открытые игры на данный момент
Response:
{
	"id": 1,
	"opened":true,
	"firstPlayer":Ilya,
	"secondPlayer":Anton
}

POST /game/{gameId}/connect - подключиться к открытой игре
Request:
{
	"gameId":1,
	"name":Ilya
}
Response:
{
	"successful":true,
	"game":{
			"id":1,
			"opened":false,
			"firstPlayer":Genadiy,
			"secondPlayer":Ilya
			}
}
