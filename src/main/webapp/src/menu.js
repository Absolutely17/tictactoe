import ReactDOM from "react-dom";
import React from "react";
import axios from 'axios';

class Menu extends React.Component {
    constructor(props) {
        super(props);

        this.joinClick = this.joinClick.bind(this);
        this.newGameClick = this.newGameClick.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    state ={
        games : [],
        name : ''
    };
    newGameClick(){
        axios.post('http://localhost:8080/game/create',
            {name:this.state.name})
            .then(res => {
                window.location.assign('/game?id=' + res.data.id + '&name=' + this.state.name);
            })
    }
    componentDidMount() {
        axios.get('http://localhost:8080/games')
            .then(res => {
                const games = res.data;
                this.setState({games});
            })
    }
    joinClick(data){
        axios.post('http://localhost:8080/game/' + data + '/connect',
            {gameId : data,
            name:this.state.name})
            .then(() => {
                window.location.assign('/game?id=' + data + '&name=' + this.state.name);
            })
    }
    handleChange(event)
    {
        this.setState({name:event.target.value});
    }
    render() {
        const openGames = [];

            openGames.push(this.state.games.map(game => <td>{game.id}{game.firstPlayer} - {game.secondPlayer}</td>))

        return (
            <div class="menu">
            <div className="menuButton">
                <input type="text" value={this.state.name} onChange={this.handleChange}></input>
                <button onClick={() => this.newGameClick()} className="startGame">Start New Game</button>
            </div>
                <div className="listGames">
                    {this.state.games.map((data, i) => (
                        <tr key={i}>
                            <td>{data.id}</td>
                            <td>{data.firstPlayer}</td>
                            <td>{data.secondPlayer}</td>
                            <button onClick={() => this.joinClick(data.id)}></button>
                        </tr>
                    ))}
                </div>
            </div>
        )
    }
}
export default Menu;