import ReactDOM from "react-dom";
import React from "react";
import axios from 'axios';

class Menu extends React.Component {
    constructor() {
        super();

        this.joinClick = this.joinClick.bind(this)
    }
    state ={
        games : []
    };
    onClick(){
        window.location.assign('/game')
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
            {gameId : data})
            .then(() => {
                window.location.assign('/game?id=' + data);
            })
    }
    render() {
        const openGames = [];

            openGames.push(this.state.games.map(game => <td>{game.id}{game.firstPlayer} - {game.secondPlayer}</td>))

        return (
            <div class="menu">
            <div className="menuButton">
                <button onClick={(e) => this.onClick(e)} className="startGame">Start New Game</button>
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