import React from "react";
import axios from 'axios';

class Square extends React.Component {
    render() {
        return (
            <button className="square"
                    onClick={() => this.props.onClick( )}>
                {this.props.value}
            </button>
        );
    }
}

class Board extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            squares: Array(361).fill(null),
            id : this.props.id,
            name : this.props.name,
            mark : 'X',
            lastMove : this.props.lastMove
        };
        this.tick();
    }
    handleClick(i){
        axios.post('http://localhost:8080/game/' + this.state.id + '/move',
            {cell:i,
            name:this.state.name})
            .then(res =>{
                if (res.data.successful===true)
                    this.moveTo(i);
            });
    }
    moveTo(i){
        const squares = this.state.squares.slice();
        squares[i]=this.state.mark;
        this.setState({squares:squares});
    }
    renderSquare(i) {
        return (<Square
                value={this.state.squares[i]}
                onClick={() => this.handleClick(i)}
            />
        );
    }
    componentDidMount() {

        this.timerID = setInterval(
            () => this.tick(),
            1000
        );

    }
    tick() {
        axios.get('http://localhost:8080/game/' + this.state.id + '/state')
            .then(res => {
                const dataFromServer = res.data;
                this.setState({data:dataFromServer});
                if (res.data.secondPlayer === null)
                    this.setState({info:'Ожидаем второго игрока.'});
                if (res.data.winner!==null)
                    this.setState({info:'Победил ' + res.data.winner});
                if (res.data.currentMove!==null)
                    this.setState({info:'Время ходить, ' + res.data.currentMove});
                if (res.data.lastMove!==-1)
                   this.moveTo(res.data.lastMove);
            })
    }
    render() {
        const table = [];
        for (var i=0;i<19;i++) {
            const children = [];
            for (var k = 0; k < 19; k++)
                children.push(this.renderSquare(19*i+k));
            table.push(<div className={'board-row'}>{children}</div>);
        }
        return (
            <div>
                <div className="info">
                    <p>{this.state.info}</p>
                </div>
            <div>

                {table}
            </div>
            </div>
        );
    }
}
class Game extends React.Component {
    constructor(props)
    {
        super(props);
        const params = new URLSearchParams(props.location.search);
        this.state = {
            id : params.get('id'),
            name : params.get('name')
        };
    }

    render() {
        return (
            <div className="game">
                <div className="game-info">

                </div>
                <div className="game-board">
                    <Board id={this.state.id} name={this.state.name} />
                </div>

            </div>
        );
    }
}
export default Game;