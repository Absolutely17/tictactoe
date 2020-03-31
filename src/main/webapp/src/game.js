import React from "react";

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
            squares: Array(361).fill(null)
        };
    }
    handleClick(i){
        const squares = this.state.squares.slice();
        squares[i]='X';
        this.setState({squares:squares});
    }
    renderSquare(i) {
        return (<Square
                value={this.state.squares[i]}
                onClick={() => this.handleClick(i)}
            />
        );
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
                {table}
            </div>
        );
    }
}
class Game extends React.Component {

    render() {
        return (
            <div className="game">
                <div className="game-board">
                    <Board />
                </div>
                <div className="game-info">
                    <div>{/* status */}</div>
                    <ol>{/* TODO */}</ol>
                </div>
            </div>
        );
    }
}
export default Game;