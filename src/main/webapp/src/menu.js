import React from "react";
import API from "./API";

class Menu extends React.Component {
    constructor(props) {
        super(props);
        this.joinClick = this.joinClick.bind(this);
        this.spectateClick = this.spectateClick.bind(this);
        this.newGameClick = this.newGameClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            games: [],
            name: '',
            showAllGames:false
        };
        this.tick();
    }
    handleChange(event)
    {
        const target = event.target;
        const name = target.name;
        const value = name === 'showAllGames' ? target.checked : target.value;
        this.setState({[name]:value});
        console.log(this.state.showAllGames);
    }
    newGameClick(){
        API.post('/game/create',
            {name:this.state.name})
            .then(res => {
                window.location.assign('/game?id=' + res.data.id + '&name=' + this.state.name);
            })
    }
    joinClick(gameId){
        API.post('/game/' + gameId + '/connect',
            {gameId : gameId,
                name:this.state.name})
            .then(() => {
                window.location.assign('/game?id=' + gameId + '&name=' + this.state.name);
            })
    }
    spectateClick(gameId){
        window.location.assign('/game?id=' + gameId);
    }
    componentDidMount() {
        this.timerID = setInterval(
            () => this.tick(),
            5000
        );
    }
    componentDidUpdate(prevProps, prevState, snapshot) {
        if (this.state.showAllGames!==prevState.showAllGames)
            this.tick();
    }

    tick() {
        API.get('/games?isAll=' + this.state.showAllGames)
            .then(res => {
                const games = res.data;
                this.setState({games});
            })
    }
    render() {
        return (
            <div className="menu">
                <div className="menu-column">
            <div className="menuAttr">
                <input name="name" placeholder="Enter name" className="inputName" type="text" value={this.state.name} onChange={this.handleChange}/>
                    <div className="isAllGames">
                    <input id="allGames" name="showAllGames" type="checkbox"  checked={this.state.showAllGames} onChange={this.handleChange}/>
                    <label htmlFor="allGames">Показать все игры</label>
                    </div>
                <button onClick={() => this.newGameClick()} className="startGameBtn">Start New Game</button>
            </div>
                <div className="listGames">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Имя первого игрока</th>
                        <th>Имя второго игрока</th>
                        <th>Действие</th>
                    </tr>
                    </thead>
                    {this.state.games.map((data, i) => (
                        <tr key={i}>
                            <td>{data.id}</td>
                            <td>{data.firstPlayer}</td>
                            <td>{data.secondPlayer}</td>
                            <td>{data.opened ? (<button className="joinBtn" onClick={() => this.joinClick(data.id)}>Присоединиться</button>) : null}
                            <button className="spectateBtn" onClick={() => this.spectateClick(data.id)}>Наблюдать</button></td>
                        </tr>
                    ))}
                </div>
                </div>
            </div>
        )
    }
}
export default Menu;