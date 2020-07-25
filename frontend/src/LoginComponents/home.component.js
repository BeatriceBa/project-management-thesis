import React, { Component } from "react";
import Card from 'react-bootstrap/Card'
import CardGroup from 'react-bootstrap/CardGroup'

export default class Home extends Component {
	constructor(props) {
		super(props);

		this.state = {
				content: ""
		};
	}


	render() {
		return (
				<> <Card>
					<Card.Body>
						<blockquote className="blockquote mb-0">
							<p>
								{' '}
								If you cannot explain it to a six years old, you do not understand it yourself .{' '}
							</p>
							<footer className="blockquote-footer"> 
								<cite title="Source Title">Albert Einstein</cite> 
							</footer>
						</blockquote>
					</Card.Body>
				</Card>

				<div>
					<CardGroup>
						<Card>
							<Card.Img variant="top" src='https://source.unsplash.com/random/1080x720' />
								<Card.Body>
									<Card.Title>Secure login</Card.Title>
									<Card.Text>
										Do not worry about your passwords being stored, they are encrypted.
										Mind that this is on a local database, so you should not worry
										about it anyway.
									</Card.Text>
								</Card.Body>
							<Card.Footer>
								<small className="text-muted">Appreciate the effort</small>
							</Card.Footer>
						</Card>
						
						<Card>
							<Card.Img variant="top" src='https://source.unsplash.com/random/1080x721' />
								<Card.Body>
									<Card.Title>Simplicity is the key</Card.Title>
									<Card.Text>
										With a minimalistic interface, this web application has nothing to 
										envy to business applications for which you have to pay lots of money.
										Or does it?
									</Card.Text>
								</Card.Body>
							<Card.Footer>
								<small className="text-muted">Call me microsoft</small>
							</Card.Footer>
						</Card>
						
						<Card>
							<Card.Img variant="top" src='https://source.unsplash.com/random/1081x720' />
								<Card.Body>
									<Card.Title>Lots of cool APIs</Card.Title>
									<Card.Text>
										For instance this random images are so cool! I used them
										to cover my lack of creativity and inability to develop
										aesthetically pleasing pages.
									</Card.Text>
								</Card.Body>
							<Card.Footer>
								<small className="text-muted">My master degree will be graphic design, obviously</small>
							</Card.Footer>
						</Card>
					</CardGroup>
				</div> </>
		);
	}
}