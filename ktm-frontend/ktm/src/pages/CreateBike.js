import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form'

const CreateBike = () => {
  const [error] = useState("")
  const { register, formState: { errors } } = useForm()
  const navigate = useNavigate()


 

  const cancel = () => {
    navigate("/")
  }

  return (
    <div>
      <br />
      <div className='container'>
        <div className='row'>
          <div className='card col-md-6 offset-md-3 offset-md-3'>
            <h3 className='text-center'>ADD USER</h3>
            <hr />
            <form >
              <div className='row'>
                <div className='col-md-6'>
                  <label>First Name:<span className='required-star' >*</span></label>
                  <input
                    type='text'
                    className='form-control'
                    placeholder='Enter First Name'
                    required
                    {...register("firstName",
                      { required: "*First name is required" }
                    )}
                  />
                </div>
                <div className='col-md-6'>
                  <label>Last Name:<span className='required-star' >*</span></label>
                  <input
                    type='text'
                    className='form-control'
                    placeholder='Enter last Name'
                    {...register("lastName",
                      { required: "*Last name is required" }
                    )}
                  />
                </div>
              </div>
              <p>{errors.firstName?.message || errors.lastName?.message}</p>
              <div>
                <label>User Name:<span className='required-star' >*</span></label>
                <input
                  type='text'
                  className='form-control'
                  name='userName'
                  placeholder='Enter username'
                  {...register("userName",
                      { required: "*User name is required" }
                    )}
                  // onChange={(e) => checkUserNameAvailability(e)}                  
                />
              </div>
              <p>{error || errors.userName?.message}</p>
              <div>
                <label>Email Id:<span className='required-star' >*</span></label>
                <input
                  type='email'
                  className='form-control'
                  placeholder='Enter Email Id'                  
                  {...register("emailId",
                    { required: "*Email ID is required" }
                  )}
                />
              </div>
              <p>{errors.emailId?.message}</p>
              <div>
                <label>Mobile Number:<span className='required-star' >*</span></label>
                <input
                  type='number'
                  className='form-control'
                  placeholder='Enter Mobile Number'
                  {...register("mobileNo",
                    {
                      required: "*Mobile Number is required",
                      minLength: {
                        value: 10,
                        message: "*Mobile Number should contain 10-11 digits"
                      },
                      maxLength: {
                        value: 11,
                        message: "*Mobile Number should contain 10-11 digits"
                      }
                    }
                  )}
                />
              </div>
              <p>{errors.mobileNo?.message}</p>
              <div>
                <label>Address:<span className='required-star' >*</span></label>
                <textarea
                  type='text'
                  className='form-control'
                  placeholder='Enter address'
                  {...register("address",
                    { required: "*Address is required" }
                  )}
                />
              </div>
              <p>{errors.address?.message}</p>
              <button type='submit' className='btn btn-primary'>Submit</button>
              <button className='btn btn-danger' onClick={(e) => cancel()} style={{ "marginLeft": 20 }}>Cancel</button>
              <br />
            </form>
            <br />
          </div>
        </div>
        <br /><br />
      </div>
    </div>
  )
}

export default CreateBike;