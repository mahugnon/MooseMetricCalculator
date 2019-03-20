Class {
	#name : #MMCQueryLocalWith,
	#superclass : #MMCAbstractQuery,
	#category : #'Moose-Metric-Calculator-Query'
}

{ #category : #accessing }
MMCQueryLocalWith class >> displayName [
	^ 'queryLocal:with:'
]

{ #category : #accessing }
MMCQueryLocalWith >> searchAssociations [
	^ self seachAssociationWithAssociationClass 
]
