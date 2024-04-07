import React from "react"
import { Button } from "react-bootstrap"
import { Link } from "react-router-dom"

const Admin = () => {
	return (
		<section className="container mt-5">
			<h2>Welcome to Admin Panel</h2>
			<hr />
			<center><button><Link to={"/existing-rooms"} class="text-model a" >Manage Rooms</Link> 
			
			</button>
			<br /><br />
		<button>	<Link to={"/existing-bookings"} class="text-model a">Manage Bookings</Link>
		
	  </button></center>
		</section>
	)
}

export default Admin
